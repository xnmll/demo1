package cn.xnmll.sbdemo.cache;

import cn.xnmll.sbdemo.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TagCache {
    public static List<TagDTO> get(){
        ArrayList<TagDTO> objects = new ArrayList<>();
        TagDTO t1 = new TagDTO();
        t1.setCategoryName("开发语言");
        t1.setTags(Arrays.asList("js","php","css","html","nodejs","asp.net","python","go","c","c++","c#"));
        objects.add(t1);
        TagDTO t2 = new TagDTO();
        t2.setCategoryName("平台框架");
        t2.setTags(Arrays.asList("linux","koa","struts","flask","django","laravel","unix","macos","cs","bs"));
        objects.add(t2);
        TagDTO t3 = new TagDTO();
        t3.setCategoryName("服务器");
        t3.setTags(Arrays.asList("linux","docker","ubuntu","hadoop","windows","apache","unix","macos","nginx","kafka"));
        objects.add(t3);
        return objects;
    }
    public static String filterInvalid(String tags){
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();
        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
