# Giant Haystacks

Reads needles from a file then searches the haystack from a file if specified, or from stdin if not.

**This simplified version of the main branch to test how a few different language compare at this task.**

## Usage

```
$ java -jar build/libs/haystacks-1.0-SNAPSHOT-all.jar --help
Usage: haystacks [-h] [--haystack=<haystack>] --needles=<needles>
      --haystack=<haystack>
      --needles=<needles>

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

