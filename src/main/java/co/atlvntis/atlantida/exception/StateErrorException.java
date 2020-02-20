package co.atlvntis.atlantida.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StateErrorException extends Exception {

    private final String message;

}
