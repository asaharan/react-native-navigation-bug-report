import React, { useCallback, useEffect, useState } from "react";
import {
  ScrollView,
  StyleSheet,
  Text,
  TouchableOpacity,
  View
} from "react-native";
import ListItem from "./ListItem";

const Button = TouchableOpacity;
const Spinner = Text;

const Dimensions = {
  unit: 8
};
const Colors = {
  primary: "#430000"
};

const getList = () => {
  return new Promise(resolve => {
    resolve([]);
  });
};

const sessions = [];
for (var i = 0; i < 100; i++) {
  sessions.push(`id-${i}`);
}

const state = {
  session: {
    sessions
  }
};

const useSelector = callback => {
  callback(state);
};

const SessionList = props => {
  const { isVisible, sessions } = props;
  const [isFetching, setIsFetching] = useState(false);
  const [isFetched, setIsFetched] = useState(false);
  const [error, setError] = useState(null);
  const fetchSessionList = useCallback(() => {
    setIsFetching(true);
    setError(null);
    getList()
      .then(() => {
        setIsFetched(true);
        setIsFetching(false);
      })
      .catch(e => {
        setError(
          e && e.message
            ? e.message
            : "Some error occurred while fetching session list"
        );
        setIsFetching(false);
      });
  }, []);
  useEffect(() => {
    if (isVisible && !isFetched) {
      fetchSessionList();
    }
  }, [fetchSessionList, isVisible, isFetched]);
  const onContinuePractice = s => {
    console.log(s);
  };
  const onShowSessionDetail = onContinuePractice;
  if (!isFetched && (isFetching || error)) {
    return (
      <View style={[styles.spinnerContainer]}>
        {error ? (
          <View style={[styles.errorContainer]}>
            <Text style={[styles.error]}>{error}</Text>
            <Button onPress={fetchSessionList}>
              <Text>Try Again</Text>
            </Button>
          </View>
        ) : (
          <Text>Loading</Text>
        )}
      </View>
    );
  }
  return (
    <ScrollView style={[styles.root]}>
      {sessions &&
        sessions.map((sessionId, index) => {
          return (
            <ListItem
              key={sessionId}
              onResumeSession={() => {
                onContinuePractice(sessionId);
              }}
              onShowSessionDetail={() => {
                onShowSessionDetail(sessionId);
              }}
              sessionId={sessionId}
            />
          );
        })}
    </ScrollView>
  );
};

SessionList.defaultProps = {
  sessions
};

const styles = StyleSheet.create({
  spinnerContainer: {
    height: "100%",
    width: "100%",
    justifyContent: "center",
    alignItems: "center"
  },
  errorContainer: {
    alignItems: "center"
  },
  error: { marginBottom: Dimensions.unit },
  root: { padding: Dimensions.unit, paddingTop: 0, width: "100%" }
});

export default SessionList;
