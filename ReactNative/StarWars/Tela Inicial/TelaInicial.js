import React from 'react';
import {Text, View, Image, Button} from 'react-native';
import Logo from '../assets/logo.jpg';
import styles from '../styles';


export default function TelaInicial(props){
    return(
        <View style={styles.container}>
            <Text>Star Wars ordem cornológica</Text>
            <Image source={Logo} style={styles.imagem}/>
            
            <Text style={styles.titulo}>
                Voce sabe qual é a ordem cornológica da franquia Star Wars?
            </Text>

            <Text style={styles.textos}>
                Os principais filmes da franquia estão divididos em e trilogias. Clique no botão a seguir para conferir.
            </Text>
            <Button title="Ver 1ª Trilogia" onPress={() => props.navigation.navigate("Trilogia 1")} color="#372d00"/>
            <Button title="Ver 2ª Trilogia" onPress={() => props.navigation.navigate("Trilogia 2")} color="#372d00"/>
            <Button title="Ver 3ª Trilogia" onPress={() => props.navigation.navigate("Trilogia 3")} color="#372d00" />             
        </View>
    );
}