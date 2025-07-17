#!/bin/bash

REPORT_FILE=$1  # e.g. target/surefire-reports/emailable-report.html

echo "### ✅ Build Summary" >> $GITHUB_STEP_SUMMARY
echo "- **Build Status**: Success ✅" >> $GITHUB_STEP_SUMMARY
echo "- **Branch**: $GITHUB_REF_NAME" >> $GITHUB_STEP_SUMMARY
echo "- **Commit**: [$GITHUB_SHA](https://github.com/${GITHUB_REPOSITORY}/commit/${GITHUB_SHA})" >> $GITHUB_STEP_SUMMARY

echo "" >> $GITHUB_STEP_SUMMARY
echo "### 📄 Test Report" >> $GITHUB_STEP_SUMMARY
echo "- [Download HTML Report Artifact](#artifacts)" >> $GITHUB_STEP_SUMMARY
echo "- Report file: \`$REPORT_FILE\`" >> $GITHUB_STEP_SUMMARY

# Optional: show test stats if available
TOTAL_TESTS=$(grep -oP '(?<=<td>Tests run: )\d+' $REPORT_FILE)
FAILED_TESTS=$(grep -oP '(?<=Failures: )\d+' $REPORT_FILE)
SKIPPED_TESTS=$(grep -oP '(?<=Skipped: )\d+' $REPORT_FILE)

if [[ $TOTAL_TESTS ]]; then
  echo "" >> $GITHUB_STEP_SUMMARY
  echo "| Total | Failed | Skipped |" >> $GITHUB_STEP_SUMMARY
  echo "|-------|--------|---------|" >> $GITHUB_STEP_SUMMARY
  echo "| $TOTAL_TESTS | $FAILED_TESTS | $SKIPPED_TESTS |" >> $GITHUB_STEP_SUMMARY
fi
