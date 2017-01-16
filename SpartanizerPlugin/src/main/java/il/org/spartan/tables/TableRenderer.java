package il.org.spartan.tables;

import java.util.*;

import il.org.spartan.*;

/** @author Yossi Gil <tt>yossi.gil@gmail.com</tt>
 * @since 2016-12-25 */
public interface TableRenderer {
  enum builtin implements TableRenderer {
    TXT, TEX {
      @Override public String afterHeader() {
        return "\\midrule" + NL;
      }

      @Override public String afterTable() {
        return "\\bottomrule" + NL;
      }

      // @formatter:off
      @Override public String arraySeparator() { return ", "; }
      @Override public String beforeFooter() { return "\\midrule" + NL; }
    // @formatter:on
      @Override public String beforeTable() {
        return "\\toprule" + NL;
      }

      @Override public String null¢() {
        return "$\\#$";
      }

      @Override public String recordEnd() {
        return " \\\\" + NL;
      }

      @Override public String recordSeparator() {
        return "\t&\t";
      }

      @Override public String render(final Statistic ¢) {
        switch (¢) {
          default:
            return "\\hfill" + super.render(¢);
          case min:
          case max:
            return "\\hfill" + "$\\" + super.render(¢) + "$";
          case σ:
            return "\\hfill" + "$\\sigma$";
          case Σ:
            return "\\hfill" + "$\\Sum$";
        }
      }
    },
    TEX2 {
      @Override public String afterHeader() {
        return "\\hline" + NL;
      }

      @Override public String afterTable() {
        return "\\hline" + NL;
      }

    // @formatter:off
    @Override public String arraySeparator() { return ", "; }
    @Override public String beforeFooter() { return "\\hline" + NL; }
  // @formatter:on
      @Override public String beforeTable() {
        return "\\hline" + NL;
      }

      @Override public String footerEnd() {
        return "\\\\" + NL;
      }

      @Override public String recordSeparator() {
        return "\t&\t";
      }
    },
    CSV {
    // @formatter:off
    @Override public String footerEnd() { return NL; }
    @Override public String recordSeparator() { return ","; }
    // @formatter:on
    },
    MARKDOWN {
      @Override public String afterHeader() {
        String $ = "| ";
        for (int ¢ = 0; ¢ < lastSize; ++¢)
          $ += "--- |";
        return $ + NL;
      }

      @Override public String afterTable() {
        return NL;
      }

      // @formatter:off
      @Override public String beforeTable() { return NL; }
      @Override public String recordBegin() { return "|" ; }
      @Override public String recordEnd() { return " |" + NL; }
      @Override public String recordSeparator() { return " | "; }
    // @formatter:on
    };
    static int lastSize;

    @Override public void setHeaderCount(final int size) {
      builtin.lastSize = size;
    }
  }

  String NL = System.getProperty("line.separator");

  default String cellReal(final Double ¢) {
    return ¢.longValue() != ¢.doubleValue() ? ¢ + "" : cellInt(Long.valueOf(¢.longValue()));
  }

  static String empty() {
    return "";
  }

  static String tab() {
    return "\t";
  }

  default String afterFooter() {
    return empty();
  }

  default String afterHeader() {
    return empty();
  }

  default String afterTable() {
    return empty();
  }

  // @formatter:off
  default String arraySeparator() { return "; "; }
  default String beforeFooter() { return empty(); }
  default String beforeHeader() { return empty(); }
  default String beforeTable() { return empty(); }
  default String cellArray(final Object[] ¢) {
    return separate.these(¢).by(arraySeparator());
  }
  default String cellInt(final Long ¢) { return ¢ + ""; }
  default String extension() {
    return toString().toLowerCase();
  }
  default String footerBegin() { return recordBegin();}
  default String footerEnd() { return recordEnd();}
  default String footerSeparator() { return recordSeparator(); }
  default String headerLineBegin() { return recordBegin(); }
  default String headerLineEnd() { return recordEnd(); }
  default String headerSeparator() { return recordSeparator(); }
  default String null¢() { return "Nº"; }
  // @formatter:on
  default String recordBegin() {
    return empty();
  }

  default String recordEnd() {
    return NL;
  }

  default String recordSeparator() {
    return tab();
  }

  default String render(final Statistic ¢) {
    return ¢ + "";
  }

  default String renderRow(final Collection<Object> values) {
    final StringBuilder $ = new StringBuilder(recordBegin());
    final Separator s = new Separator(recordSeparator());
    for (final Object ¢ : values)
      $.append(s)
          .append(¢ instanceof Object[] ? cellArray((Object[]) ¢)
              : ¢ instanceof Integer ? cellInt(Long.valueOf(((Integer) ¢).intValue())) //
                  : ¢ instanceof Long ? cellInt((Long) ¢) //
                      : ¢ instanceof Double ? cellReal((Double) ¢) //
                          : ¢);
    return $ + recordEnd();
  }

  void setHeaderCount(int size);

  default String stringField(final String value) {
    return value;
  }
}