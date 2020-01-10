import React from "react";
import { StyleSheet, Text, TouchableOpacity, View } from "react-native";

const Dimensions = {
  unit: 8
};
const Colors = {
  primary: "#430000"
};

const session = { title: "Title" };

const useSelector = () => {
  return session;
};

const ListItem = ({ sessionId, onShowSessionDetail, onResumeSession }) => {
  const session = useSelector(state => state.session.sessionsById[sessionId]);
  if (!session) {
    return null;
  }
  const isLive = !session.endTime;
  let duration = "duration minutes";
  let durationUnit = "min";
  if (duration < 2) {
    duration = "9 seconds duration";
    durationUnit = "sec";
  }
  return (
    <TouchableOpacity
      activeOpacity={0.4}
      style={styles.root}
      onPress={isLive ? onResumeSession : onShowSessionDetail}
    >
      <View style={styles.row}>
        <View>
          <View style={styles.primaryTextContainer}>
            <Text style={styles.primaryText}>{session.title}</Text>
          </View>
          <View style={styles.secondaryTextContainer}>
            <Text style={styles.secondaryText}>{"MMM D, YYYY hh:mm A"}</Text>
          </View>
        </View>
        <View style={styles.row}>
          {isLive ? (
            <View style={styles.row}>
              <View style={styles.dot} />
              <Text style={styles.textLive}>Live</Text>
            </View>
          ) : null}
        </View>
      </View>
    </TouchableOpacity>
  );
};
const styles = StyleSheet.create({
  root: {
    paddingTop: Dimensions.unit * 2,
    paddingBottom: Dimensions.unit * 2,
    paddingLeft: Dimensions.unit,
    paddingRight: Dimensions.unit,
    height: 64
  },
  row: {
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between"
  },
  primaryTextContainer: {
    height: 28
  },
  primaryText: {
    color: Colors.text,
    fontSize: Dimensions.listPrimaryFontSize
  },
  secondaryTextContainer: {
    height: 20
  },
  primaryButton: {
    marginRight: Dimensions.unit
  },
  secondaryText: {
    color: Colors.textLight,
    fontSize: Dimensions.listSecondaryFontSize
  },
  dot: {
    borderWidth: 2,
    borderColor: Colors.success,
    borderRadius: 2,
    marginRight: Dimensions.unit / 2
  },
  textLive: {
    color: Colors.success
  }
});

export default ListItem;
