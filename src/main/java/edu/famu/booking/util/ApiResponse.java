package edu.famu.booking.util;

public record  ApiResponse(boolean success, String message, Object data, Object error) {}
