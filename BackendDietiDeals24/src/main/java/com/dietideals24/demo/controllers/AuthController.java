package com.dietideals24.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dietideals24.demo.configurations.JwtAuthenticationResponse;
import com.dietideals24.demo.configurations.JwtTokenProvider;
import com.dietideals24.demo.models.Utente;
import com.dietideals24.demo.models.dto.UtenteDTO;
import com.dietideals24.demo.service.UtenteService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UtenteService utenteService;
    
    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, UtenteService utenteService) {
    	this.authenticationManager = authenticationManager;
    	this.tokenProvider = tokenProvider;
    	this.utenteService = utenteService;
    }

	
    @PostMapping("/google")
    public ResponseEntity<Object> googleLogin(@RequestParam("idToken") String idToken) {
        try {
            GoogleIdToken.Payload payload = tokenProvider.verifyGoogleToken(idToken);
            String email = payload.getEmail();
            String nome = (String) payload.get("name");

            Utente utente = utenteService.recuperaUtenteByEmail(email);
            String jwtToken;
            if (utente == null) {
                UtenteDTO utenteDto = new UtenteDTO();
                utenteDto.setEmail(email);
                utenteDto.setUsername(nome);
                utenteDto.setPassword("");
                utenteDto = utenteService.registraUtente(utenteDto);
                jwtToken = tokenProvider.generateTokenFromUserId(utenteDto.getId());
            } else {
                jwtToken = tokenProvider.generateTokenFromUserId(utente.getId());            	
            }

            return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token google non valido");
        }
    }
	
	 @PostMapping("/login")
		public ResponseEntity<Object> login(@RequestBody UtenteDTO utenteDto) {
		 	try {
				Authentication authentication = authenticationManager.authenticate(
		                new UsernamePasswordAuthenticationToken(
		                		utenteDto.getEmail(), utenteDto.getPassword()));
	
		        SecurityContextHolder.getContext().setAuthentication(authentication);
		        
		        String token = tokenProvider.generateToken(authentication);
		        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
		 } catch (AuthenticationException e) {
		        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenziali non valide");
		    } 
		}
	
}
