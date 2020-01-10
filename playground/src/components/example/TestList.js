import React from "react";
import { useSelector } from "react-redux";
import { requireNativeComponent, StyleSheet } from "react-native";

const TestList = requireNativeComponent("TestList");
const dummyAssessment = {
  _id: "5e011085c390eb7d0a9b6b7d",
  duration: 900,
  graded: true,
  key: "5e011085c390eb7d0a9b6b7d",
  label: "Inner tab #1",
  name: "Numbers-Easy-Test-1",
  reward: 250,
  submission: null,
  type: "TOPIC-MOCK"
};

const assessments = [];
const innerTabs = [];
for (var j = 0; j < 10; j++) {
  innerTabs.push({ ...dummyAssessment, label: `Inner tab ${j + 1}` });
}
innerTabs.forEach(d => {
  for (var i = 0; i < 10; i++) {
    assessments.push(d);
  }
});

const tab = {
  title: "Tab 1",
  assessments
};

const tabs = [];
for (var i = 0; i < 4; i++) {
  tabs.push({ ...tab, title: `Tab #${i}` });
}
const TestListWrapper = props => {
  const { tabs } = props;

  const handleClickAssessment = event => {
    const { _id: assessmentId } = event.nativeEvent;
    console.log(assessmentId);
  };
  return (
    <TestList
      onClickAssessment={handleClickAssessment}
      tabs={tabs}
      style={styles.mainComponent}
    />
  );
};

TestListWrapper.defaultProps = {
  tabs
};

const styles = StyleSheet.create({
  mainComponent: {
    height: 400,
    width: "100%"
  }
});

export default TestListWrapper;
