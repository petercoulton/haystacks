package com.petercoulton.toolbox.haystacks;

import com.google.common.base.Splitter;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static picocli.CommandLine.Option;

@Command(name = "haystacks")
public class Main implements Runnable {

	@Option(names = {"--needles"}, required = true)
	public File needles;

	@Option(names = {"--haystack"})
	public File haystack;

	@Option(names = {"-d", "--delimiter"})
	public String delimiter = ",";

	@Option(names = {"-c", "--column"})
	public int column = 1;

	@Option(names = {"-h", "--help"}, usageHelp = true)
	private boolean helpRequested = false;

	private Splitter splitter;

	public static void main(String... args) {
		CommandLine.run(new Main(), args);
	}

	@Override
	public void run() {
		try {
			final Set<String> needles =
					Files.lines(this.needles.toPath())
					     .collect(Collectors.toSet());

			splitter = Splitter.on(this.delimiter).trimResults();

			final List<String> matches =
					getHaystack()
							.filter(line -> needles.contains(getLineColumn(line, this.column)))
							.collect(Collectors.toList());

			matches.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getLineColumn(final String line, int column) {
		final List<String> columns = this.splitter.splitToList(line);
		if (columns.size() >= column) {
			return columns.get(column - 1);
		} else {
			return "";
		}
	}

	private Stream<String> getHaystack() throws IOException {
		if (this.haystack == null) {
			return InputStreamLines.fromStdIn();
		} else {
			return Files.lines(this.haystack.toPath());
		}
	}

}


