package com.forteanuncio.prep.dadospublicoscnpj.converters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.forteanuncio.prep.dadospublicoscnpj.utils.Utils.GET_METHOD;
import static com.forteanuncio.prep.dadospublicoscnpj.utils.Utils.firstUpperNameField;

public class SSTableConverter<T> {

    private static final Logger logger = LoggerFactory.getLogger(CsvConverter.class);
    
    public List<Object> convertLineToListObjectTypes(Object model, String genericHeader) {
        List<Object> columnsOfTable = new ArrayList<Object>();
        try{
            String[] headerColumns = genericHeader.split(",");
            for(int i=0; i < headerColumns.length; i++){
                Method method = model.getClass().getDeclaredMethod(GET_METHOD+firstUpperNameField(headerColumns[i]));
                columnsOfTable.add(method.invoke(model));
            }
        }catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException  e){
            logger.error("Error on mapping of List<Object> for SSTable. Details: {}", e.getMessage());
        }
        return columnsOfTable;
    }

}