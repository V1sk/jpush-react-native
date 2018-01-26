import React from 'react'
import ReactNative from 'react-native'
import JPushModule from 'jpush-react-native'

const { View, Text, TouchableHighlight, StyleSheet } = ReactNative

export default class Second extends React.Component {
  onBackPress = () => {
    if (!this.props.navigator) {
      this.props.navigator.pop()
    } else {
      console.log('finishing second activity')
      JPushModule.finishActivity()
    }
  }

  componentDidMount () {
    JPushModule.addReceiveExtrasListener(map => {
      console.log('Got extra, key: hello, value: ' + map.hello)
    })
  }

  onButtonPress = () => {
    console.log('will jump to setting page')
    if (!this.props.navigator) {
      this.props.navigator.push({
        name: 'setActivity'
      })
    } else {
    }
  }

  render () {
    return (
      <View>
        <TouchableHighlight
          style={styles.backBtn}
          underlayColor='#e4083f'
          activeOpacity={0.5}
          onPress={this.onBackPress}
        >
          <Text>Back</Text>
        </TouchableHighlight>
        <Text style={styles.welcome}>Welcome !</Text>
        <TouchableHighlight
          underlayColor='#e4083f'
          activeOpacity={0.5}
          style={styles.btnStyle}
          onPress={this.onButtonPress}
        >
          <Text style={styles.btnTextStyle}>Jump To Setting page!</Text>
        </TouchableHighlight>
      </View>
    )
  }
}

var styles = StyleSheet.create({
  backBtn: {
    padding: 10,
    marginTop: 10,
    marginLeft: 10,
    borderWidth: 1,
    borderColor: '#3e83d7',
    backgroundColor: '#3e83d7',
    borderRadius: 8,
    alignSelf: 'flex-start'
  },
  welcome: {
    textAlign: 'center',
    margin: 10
  },
  btnStyle: {
    marginTop: 10,
    borderWidth: 1,
    borderColor: '#3e83d7',
    borderRadius: 8,
    backgroundColor: '#3e83d7',
    alignSelf: 'center',
    justifyContent: 'center'
  },
  btnTextStyle: {
    textAlign: 'center',
    fontSize: 25,
    color: '#ffffff'
  }
})
