# Haystacks

Reads needs from a file then searches the haystack from a file if specified or from stdin if not.

You can optionally specify a delimiter and column number to select which field in the haystack to search on.

## Usage

```
$ java -jar build/libs/haystacks-1.0-SNAPSHOT-all.jar --help
Usage: haystacks [-h] [--haystack=<haystack>] --needles=<needles> [-c=<column>]
                 [-d=<delimiter>]
      --haystack=<haystack>
      --needles=<needles>
  -c, --column=<column>
  -d, --delimiter=<delimiter>

  -h, --help
```

## Examples

From stdin

```
$ cat ./haystack.txt | java -jar build/libs/haystacks-1.0-SNAPSHOT-all.jar --needles ./needles.txt
```

From file

```
$ java -jar build/libs/haystacks-1.0-SNAPSHOT-all.jar --needles ./needles.txt --haystack ./haystack.txt
```

