package app.uvsy.model.converters;

import app.uvsy.model.Tag;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TagConverter implements DynamoDBTypeConverter<List<String>, List<Tag>> {

    @Override
    public List<String> convert(List<Tag> tags) {
        return Optional.of(tags)
                .orElseGet(ArrayList::new)
                .stream()
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public List<Tag> unconvert(List<String> tagsString) {
        return Optional.of(tagsString)
                .orElseGet(ArrayList::new)
                .stream()
                .map(Tag::valueOf)
                .collect(Collectors.toList());
    }
}
