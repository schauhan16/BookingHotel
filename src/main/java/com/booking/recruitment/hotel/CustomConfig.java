package com.booking.recruitment.hotel;

import com.booking.recruitment.hotel.sorter.CustomSortTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Shailendra Chauhan
 */

@Configuration
public class CustomConfig extends WebMvcConfigurationSupport {
    @Override
    public FormattingConversionService mvcConversionService() {
        FormattingConversionService f = super.mvcConversionService();
        f.addConverter(new CustomSortTypeConverter());
        return f;
    }
}
