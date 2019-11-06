package com.data.server.dataserver.service.impl;

import com.data.server.dataserver.dto.FieldDto;
import com.data.server.dataserver.dto.PointDto;
import com.data.server.dataserver.dto.SensorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AverageValueOfSensors
 *
 * @author Dmitriy
 */

@Getter
@AllArgsConstructor
public class AverageValueOfSensors {

   private double temperature;
   private double humidity;
   private double pressure;

   public static AverageValueOfSensors getAverage(FieldDto fieldDto){
       double temp = 0;
       double hum = 0;
       double press = 0;
       final double count = fieldDto.getSensors().size();
       for(SensorDto sensor : fieldDto.getSensors()){
         temp += sensor.getTemperature();
         hum += sensor.getHumidity();
         press += sensor.getPressure();
       }
       return new AverageValueOfSensors(temp/count,
                                        hum/count,
                                        press/count);
   }
}
