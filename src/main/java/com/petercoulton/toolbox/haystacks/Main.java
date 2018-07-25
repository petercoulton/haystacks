package com.petercoulton.toolbox.haystacks;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static picocli.CommandLine.Option;

@Command(name = "haystacks")
public final class Main implements Runnable {

	@Option(names = {"--needles"}, required = true)
	public File needles;

	@Option(names = {"--haystack"})
	public File haystack;

	@Option(names = {"-h", "--help"}, usageHelp = true)
	private boolean helpRequested = false;

	public static void main(String... args) {
		CommandLine.run(new Main(), args);
	}

	@Override
	public final void run() {
		try {
			final Set<String> needles =
					Files.lines(this.needles.toPath())
					     .collect(Collectors.toSet());

			getHaystack().filter(needles::contains)
			             .forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private final Stream<String> getHaystack() throws IOException {
		if (this.haystack == null) {
			return InputStreamLines.fromStdIn();
		} else {
			return Files.lines(this.haystack.toPath());
		}
	}

}


