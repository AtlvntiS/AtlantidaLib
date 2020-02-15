package co.atlvntis.atlantida.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StateErrorException extends Exception {

    private final String message;

}
