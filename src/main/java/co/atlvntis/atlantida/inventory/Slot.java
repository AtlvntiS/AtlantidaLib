package co.atlvntis.atlantida.inventory;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Slot {

    private final int row;
    private final int column;

}
