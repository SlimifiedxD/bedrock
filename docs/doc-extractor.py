from pathlib import Path

working = Path.cwd()
dir = working / "src" / "content" / "docs"
doc_files = list(dir.rglob("*.mdx"))

with open(working / "dump.txt", "w") as out_f:
    for doc_file in doc_files:
        with open(doc_file) as temp_f:
            out_f.write(temp_f.read() + "\n-----------------------------------------\n")
