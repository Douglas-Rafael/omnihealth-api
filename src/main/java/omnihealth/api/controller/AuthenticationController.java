package omnihealth.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import omnihealth.api.domain.user.AuthenticationData;
import omnihealth.api.domain.user.User;
import omnihealth.api.infra.security.JWTTokenData;
import omnihealth.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager manager;

	@Autowired
	private TokenService tokenService;

	@PostMapping
	@Transactional
	public ResponseEntity logIn(@RequestBody @Valid AuthenticationData data) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
		var authentication = manager.authenticate(authenticationToken);

		var tokenJWT = tokenService.generateToken((User) authentication.getPrincipal());

		return ResponseEntity.ok(new JWTTokenData(tokenJWT));
	}
}
