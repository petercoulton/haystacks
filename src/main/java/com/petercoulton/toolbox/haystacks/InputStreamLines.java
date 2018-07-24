package com.petercoulton.toolbox.haystacks;

import java.io.*;
import java.util.stream.Stream;

public class InputStreamLines {

	public static Stream<String> fromStdIn() {
		return from(System.in);
	}

	public static Stream<String> from(InputStream in) {
		return lines(in);
	}

	// @see: java.nio.file.Files.lines and java.nio.file.Files.asUncheckedRunnable
	private static Stream<String> lines(final InputStream inputStream) {
		final BufferedReader input = new BufferedReader(new InputStreamReader(inputStream));

		try {
			return input.lines().onClose(closeClosable(input));
		} catch (Error | RuntimeException e) {
			try {
				input.close();
			} catch (IOException ex) {
				try {
					e.addSuppressed(ex);
				} catch (Throwable ignore) {
				}
			}
			throw e;
		}
	}

	private static Runnable closeClosable(Closeable c) {
		return () -> {
			try {
				c.close();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		};
	}
}
