package com.eBolivar.bean;

import com.eBolivar.enumeradores.AnioEnum;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created by Damian Gallego on 18/5/2018.
 */
@Component
public class AnioTypeEditor extends PropertyEditorSupport {

    public void setAsText(String text) {
        setValue(AnioEnum.fromString(text.toUpperCase()));
    }
}
