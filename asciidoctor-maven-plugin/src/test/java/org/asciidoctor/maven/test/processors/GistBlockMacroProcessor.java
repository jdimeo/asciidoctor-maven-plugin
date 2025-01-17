package org.asciidoctor.maven.test.processors;

import java.util.Map;

import org.asciidoctor.ast.Block;
import org.asciidoctor.ast.StructuralNode;
import org.asciidoctor.extension.BlockMacroProcessor;

import com.google.common.collect.ImmutableList;

public class GistBlockMacroProcessor extends BlockMacroProcessor {

    public GistBlockMacroProcessor(String macroName, Map<String, Object> config) {
        super(macroName, config);
    }

    @Override
    public Block process(StructuralNode parent, String target,
                         Map<String, Object> attributes) {

        final String content = "<div class=\"content\">\n" +
                "<script src=\"https://gist.github.com/" + target + ".js\"></script>\n" +
                "</div>";

        return createBlock(parent, "pass", ImmutableList.of(content), attributes);
    }
}
