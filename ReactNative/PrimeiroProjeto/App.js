import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, Image } from 'react-native';

import Foto from './assets/foto.png';
import Capa from './assets/capa.png';

export default function App() {
  return (
    <View style={styles.container}>
      
      <Image style={styles.Capa} source = {Capa}/>
      <View style={styles.containerDesc}>
        <Image style={styles.Foto} source = {Foto}/>
        <Text style={styles.Nome}>Robozin da DevMedia</Text>
        <Text style={styles.Texto}>Este é o Robozin da DevMedia, ele representa 
          os programadores que estudam na DevMedia</Text>
      </View>

    </View>

  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
    
  },
  containerDesc:{
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    
  },
  Capa:{
    width: 400,
    height: 400,
  },
  Foto:{
    alignItems: 'center',
    marginTop:10,
    width: 100,
    height: 100,
    borderRadius: 100,
    marginBottom: 10
  },
  Nome:{
    fontSize : 25,
    marginBottom: 10
  },
  Texto:{
    textAlign: 'center',
    fontSize: 16
  }
});
