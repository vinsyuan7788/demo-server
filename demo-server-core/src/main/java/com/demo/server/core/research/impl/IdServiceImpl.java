package com.demo.server.core.research.impl;

import com.demo.base.common.utils.DateUtils;
import com.demo.server.common.constant.CommonConstant;
import com.demo.server.common.utils.SystemUtils;
import com.demo.server.core.research.IdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author Vince Yuan
 * @date 03/02/2021
 */
@Slf4j
@Service
public class IdServiceImpl implements IdService {

    @Override
    public Long getStandaloneId() {
        // Get the string representing current date, which is 17 digits
        String prefix = DateUtils.DATE_FORMAT_CONSECUTIVE_YEAR_TO_MILLIS.format(SystemUtils.currentDate());
        // Provide the suffix with 2 random digits
        Random random = new Random();
        int[] suffixes = new int[] { random.nextInt(CommonConstant.TEN), random.nextInt(CommonConstant.TEN) };
        StringBuffer suffixBuffer = new StringBuffer();
        for (int suffix : suffixes) {
            suffixBuffer.append(suffix);
        }
        String suffix = suffixBuffer.toString();
        // Get ID, which is 19 digits that Long-typed can contain
        return Long.valueOf(prefix + suffix);
    }
}
