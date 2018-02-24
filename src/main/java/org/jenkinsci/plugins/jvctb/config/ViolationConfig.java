package org.jenkinsci.plugins.jvctb.config;

import java.io.Serializable;

import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;
import org.kohsuke.stapler.DataBoundConstructor;

import edu.umd.cs.findbugs.annotations.NonNull;
import hudson.Extension;
import hudson.model.AbstractDescribableImpl;
import hudson.model.Descriptor;
import hudson.util.ListBoxModel;
import se.bjurr.violations.lib.reports.Parser;

public class ViolationConfig extends AbstractDescribableImpl<ViolationConfig>
    implements Serializable {
  private static final long serialVersionUID = 6664329842273455651L;
  private String pattern;
  private String reporter;
  private Parser parser;

  public ViolationConfig() {}

  @DataBoundConstructor
  public ViolationConfig(String reporter, String pattern, Parser parser) {
    this.reporter = reporter;
    this.pattern = pattern;
    this.parser = parser;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ViolationConfig other = (ViolationConfig) obj;
    if (parser != other.parser) {
      return false;
    }
    if (pattern == null) {
      if (other.pattern != null) {
        return false;
      }
    } else if (!pattern.equals(other.pattern)) {
      return false;
    }
    if (reporter == null) {
      if (other.reporter != null) {
        return false;
      }
    } else if (!reporter.equals(other.reporter)) {
      return false;
    }
    return true;
  }

  public String getPattern() {
    return this.pattern;
  }

  public String getReporter() {
    if (this.reporter == null) {
      return this.parser.name();
    }
    return this.reporter;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (parser == null ? 0 : parser.hashCode());
    result = prime * result + (pattern == null ? 0 : pattern.hashCode());
    result = prime * result + (reporter == null ? 0 : reporter.hashCode());
    return result;
  }

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public void setParser(Parser parser) {
    this.parser = parser;
  }

  public Parser getParser() {
    return parser;
  }

  public void setReporter(String reporter) {
    this.reporter = reporter;
  }

  @Override
  public String toString() {
    return "ViolationConfig [pattern="
        + pattern
        + ", reporter="
        + reporter
        + ", parser="
        + parser
        + "]";
  }

  @Extension
  public static class DescriptorImpl extends Descriptor<ViolationConfig> {
    @NonNull
    @Override
    public String getDisplayName() {
      return "Violations Parser Config";
    }

    @Restricted(NoExternalUse.class)
    public ListBoxModel doFillParserItems() {
      ListBoxModel items = new ListBoxModel();
      for (Parser parser : Parser.values()) {
        items.add(parser.name());
      }
      return items;
    }
  }
}
