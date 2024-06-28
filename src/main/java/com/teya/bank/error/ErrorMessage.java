package com.teya.bank.error;

import org.springframework.http.HttpStatus;

public record ErrorMessage(HttpStatus status, String message) {}