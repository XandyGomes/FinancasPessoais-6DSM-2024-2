import React, { useState } from 'react';
import { View, Text, TextInput, Button, Alert } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import api from './api';

const LoginScreen = ({ navigation }) => {
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const handleLogin = async () => {
    try {
      const response = await api.post('/usuarios/autenticar', { email, senha });
      console.log('Resposta do servidor:', response.data);

      if (response.data) {
        const userId = response.data.id;
        console.log('ID do usuário obtido:', userId);

        await AsyncStorage.setItem('userId', String(userId));
        console.log('ID do usuário armazenado com sucesso');
        
        Alert.alert('Login realizado com sucesso!');
        navigation.navigate('Dashboard');
      } else {
        Alert.alert('Erro de login', 'Resposta inesperada do servidor');
      }
    } catch (error) {
      console.error('Erro de login:', error);
      Alert.alert('Erro de login', 'Email ou senha incorretos');
    }
  };

  return (
    <View style={{ padding: 20 }}>
      <Text style={{ fontSize: 24, marginBottom: 20 }}>Login</Text>
      <TextInput
        placeholder="Email"
        value={email}
        onChangeText={setEmail}
        style={{ height: 40, borderColor: 'gray', borderWidth: 1, marginBottom: 20, paddingHorizontal: 10 }}
      />
      <TextInput
        placeholder="Senha"
        value={senha}
        onChangeText={setSenha}
        secureTextEntry
        style={{ height: 40, borderColor: 'gray', borderWidth: 1, marginBottom: 20, paddingHorizontal: 10 }}
      />
      <Button title="Login" onPress={handleLogin} />
    </View>
  );
};

export default LoginScreen;
