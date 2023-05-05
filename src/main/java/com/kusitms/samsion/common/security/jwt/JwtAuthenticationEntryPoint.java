package com.kusitms.samsion.common.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kusitms.samsion.common.exception.dto.ErrorResponse;
import com.kusitms.samsion.common.security.exception.JwtException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TODO : 에러 반환시에 사용자 정보를 로그로 남기기(?)
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint extends OncePerRequestFilter {

	private final ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try{
			filterChain.doFilter(request, response);
		} catch (JwtException e){
			log.info("JwtException: {} {}",e.getError().getMessage(), e.getError().getErrorCode());
			setErrorCode(response, e);
		}
	}

	private void setErrorCode(HttpServletResponse response, JwtException e) throws IOException {
		ErrorResponse errorResponse = ErrorResponse.from(e);
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
	}
}
