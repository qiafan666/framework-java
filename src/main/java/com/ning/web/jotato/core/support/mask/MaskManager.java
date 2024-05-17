package com.ning.web.jotato.core.support.mask;

import com.alibaba.fastjson.JSONObject;
import com.ning.web.jotato.core.config.MaskConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 根据配置规则进行掩码
 */
@Slf4j
@Component
public class MaskManager {

    @Resource
    private MaskConfig maskConfig;

    public MaskDTO maskOne(MaskCmd req) {
        log.info("掩码一个 maskCmd={}", JSONObject.toJSONString(req));
        return mask(req);
    }

    public List<MaskDTO> batchMask(List<MaskCmd> maskReqs) {
        log.info("掩码一批 maskReqs={}", JSONObject.toJSONString(maskReqs));
        return maskReqs.stream().map(this::mask).filter(Objects::nonNull).collect(Collectors.toList());
    }

    private MaskDTO mask(MaskCmd req) {
        MaskConfig.MaskRule rule;

        if (Objects.isNull(rule = maskConfig.getRules().get(req.getRuleKey())) || Objects.isNull(rule.getValid()) || !rule.getValid()) {
            log.warn("nacos中未匹配到掩码规则 ruleKey={}", req.getRuleKey());
            return null;
        }

        MaskDTO res = new MaskDTO();
        BeanUtils.copyProperties(req, res);
        if (StringUtils.isEmpty(req.getValue())) {
            return res;
        }
        res.setResult(req.getValue().replaceAll(rule.getRegex(), rule.getReplacement()));
        if (res.getResult().equals(req.getValue())) {
            log.warn("掩码前和掩码后内容一致 req {} rule {}", JSONObject.toJSONString(req), JSONObject.toJSONString(rule));
        }
        return res;
    }
}
