package org.asciidoctor.maven.test.processors;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.BlockProcessor;
import org.asciidoctor.extension.Reader;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

public class YellBlockProcessor extends BlockProcessor {

    @SuppressWarnings("serial")
    private static Map<String, Object> configs = ImmutableMap.of(
            "contexts", ImmutableList.of(":listing"),
            "content_model", ":compound"
    );

    public YellBlockProcessor(String name, Map<String, Object> config) {
        super(name, configs);
    }

    @Override
    public Object process(StructuralNode parent, Reader reader, Map<String, Object> attributes) {
        final String upperLines = reader.readLines()
                .stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining("\n"));

        return createBlock(parent, "paragraph", ImmutableList.of(upperLines), attributes, new HashMap<>());
    }
}
