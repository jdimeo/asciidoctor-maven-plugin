package org.asciidoctor.maven.site;

import static org.mockito.Mockito.times;

import java.util.Collections;

import org.apache.maven.doxia.sink.Sink;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.collect.ImmutableList;

class HeadParserTest {

    @Test
    void should_inject_title() {
        final Sink sinkSpy = Mockito.spy(Sink.class);

        HeaderMetadata headerMetadata = new HeaderMetadata("test title", Collections.emptyList(), null);
        new HeadParser(sinkSpy).parse(headerMetadata);

        Mockito.verify(sinkSpy).head();
        Mockito.verify(sinkSpy).head_();

        Mockito.verify(sinkSpy).title();
        Mockito.verify(sinkSpy).text("test title");
        Mockito.verify(sinkSpy).title_();

        Mockito.verify(sinkSpy, times(0)).author();
        Mockito.verify(sinkSpy, times(1)).date();
    }

    @Test
    void should_inject_author() {
        final Sink sinkSpy = Mockito.spy(Sink.class);

        HeaderMetadata headerMetadata = new HeaderMetadata(null, ImmutableList.of("an author"), null);
        new HeadParser(sinkSpy).parse(headerMetadata);

        Mockito.verify(sinkSpy).head();
        Mockito.verify(sinkSpy).head_();

        verifyDefaultTitle(sinkSpy);

        Mockito.verify(sinkSpy).author();
        Mockito.verify(sinkSpy).text("an author");
        Mockito.verify(sinkSpy).author();

        Mockito.verify(sinkSpy, times(1)).date();
    }

    @Test
    void should_inject_date() {
        final Sink sinkSpy = Mockito.spy(Sink.class);

        HeaderMetadata headerMetadata = new HeaderMetadata(null, Collections.emptyList(), "2024-11-22");
        new HeadParser(sinkSpy).parse(headerMetadata);

        Mockito.verify(sinkSpy).head();
        Mockito.verify(sinkSpy).head_();

        verifyDefaultTitle(sinkSpy);

        Mockito.verify(sinkSpy, times(0)).author();

        Mockito.verify(sinkSpy).date();
        Mockito.verify(sinkSpy).text("2024-11-22");
        Mockito.verify(sinkSpy).date_();
    }

    private void verifyDefaultTitle(Sink sinkSpy) {
        Mockito.verify(sinkSpy).title();
        Mockito.verify(sinkSpy).text("[Untitled]");
        Mockito.verify(sinkSpy).title_();
    }
}
