package il.org.spartan.tables;

import java.io.*;
import java.util.*;

import il.org.spartan.*;
import org.jetbrains.annotations.NotNull;

/** TODO Yossi Gil {@code Yossi.Gil@GMail.COM} please add a description
 * @author Yossi Gil {@code Yossi.Gil@GMail.COM}
 * @since 2016-12-25 */
public class RecordWriter implements Closeable {
  /** Create a new instance, writing into a given named file
   * @param fileName the name of the output file
   * @throws IOException */
  public RecordWriter(@NotNull final TableRenderer renderer, @NotNull final String basePath) throws IOException {
    this.renderer = renderer;
    fileName = basePath.replaceAll("\\.[a-z0-9A-Z_]*$", "") + "." + renderer.extension();
    file = new File(fileName);
    writer = new FileWriter(file);
    write(renderer.beforeTable());
  }

  public void write(@NotNull final String s) {
    try {
      writer.write(s);
      writer.flush();
    } catch (@NotNull final IOException ¢) {
      throw new RuntimeException(¢);
    }
  }

  @NotNull public final File file;
  /** The name of the file into which records are written. */
  @NotNull public final String fileName;
  @NotNull public final OutputStreamWriter writer;
  @NotNull public final TableRenderer renderer;
  private boolean shouldPrintHeader = true;
  private boolean footerPrinted;

  @Override public void close() {
    try {
      if (footerPrinted)
        write(renderer.afterFooter());
      write(renderer.afterTable());
      writer.close();
    } catch (@NotNull final IOException ¢) {
      throw new RuntimeException(¢);
    }
  }

  public void write(@NotNull final Map<String, Object> ¢) {
    if (shouldPrintHeader)
      writeHeader(¢);
    shouldPrintHeader = false;
    writeData(¢);
  }

  public void writeFooter(@NotNull final Map<String, Object> ¢) {
    if (!footerPrinted) {
      write(renderer.beforeFooter());
      footerPrinted = true;
    }
    write(renderer.footerBegin() + separate.these(¢.values()).by(renderer.footerSeparator()) + renderer.footerEnd());
  }

  private void writeData(@NotNull final Map<String, Object> ¢) {
    write(renderer.renderRow(¢.values()));
  }

  private void writeHeader(@NotNull final Map<String, Object> ¢) {
    renderer.setHeaderCount(¢.size());
    write(renderer.beforeHeader() + //
        renderer.headerLineBegin() + writeHeaderInner(¢) + renderer.headerLineEnd() + //
        renderer.afterHeader());
  }

  @NotNull private String writeHeaderInner(@NotNull final Map<String, Object> m) {
    @NotNull final Separator s = new Separator(renderer.headerSeparator());
    @NotNull final StringBuilder $ = new StringBuilder();
    m.keySet().forEach(λ -> $.append(s).append(λ != null ? λ : renderer.null¢()));
    return $ + "";
  }
}
