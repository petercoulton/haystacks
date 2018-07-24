package com.petercoulton.toolbox.haystacks;

import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static picocli.CommandLine.Option;

@Command(name = "haystacks")
public class Main implements Runnable {

	@Option(names = {"--needles"}, required = true)
	public File needles;

	@Option(names = {"--haystack"}, required = true)
	public File haystack;

	@Option(names = { "-h", "--help" }, usageHelp = true)
	private boolean helpRequested = false;

	@Override
	public void run() {
		try {
			final Set<String> needles =
					Files.lines(this.needles.toPath())
					     .collect(Collectors.toSet());

			final List<String> matches =
					Files.lines(this.haystack.toPath())
					     .filter(o -> {
						     final String id = o.substring(0, o.indexOf(','));
						     return needles.contains(id.trim());
					     })
					     .collect(Collectors.toList());

			matches.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String... args) {
		CommandLine.run(new Main(), args);
	}

}


