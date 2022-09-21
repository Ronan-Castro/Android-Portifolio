import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.container}>
      <Text style={estilos.texto}>Hello World!</Text>
      <Text>Criado por Ronan na DevMedia</Text>
    </View>
  );
}

const estilos = StyleSheet.create({
  texto:{
    fontSize: 36,
    marginTop: 20
  }
});

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'lightblue',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
