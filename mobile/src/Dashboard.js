import React, { useEffect, useState } from 'react';
import { View, Text, Alert, StyleSheet } from 'react-native';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { LineChart } from 'react-native-chart-kit';
import api from './api';

const Dashboard = () => {
  const [dados, setDados] = useState([]);
  const [loading, setLoading] = useState(true);
  const [totalReceitas, setTotalReceitas] = useState(0);
  const [totalDespesas, setTotalDespesas] = useState(0);
  const [saldo, setSaldo] = useState(0);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const userId = await AsyncStorage.getItem('userId');
        console.log('ID do usuário recuperado do AsyncStorage:', userId);

        if (userId) {
          const response = await api.get('/lancamentos', {
            params: { usuario: userId },
          });
          console.log('Resposta da API para /lancamentos:', response.data);
          
          setDados(response.data);

          // Calcular receitas, despesas e saldo
          const receitas = response.data
            .filter(item => item.tipo === 'RECEITA')
            .reduce((acc, curr) => acc + curr.valor, 0);
          const despesas = response.data
            .filter(item => item.tipo === 'DESPESA')
            .reduce((acc, curr) => acc + curr.valor, 0);

          setTotalReceitas(receitas);
          setTotalDespesas(despesas);
          setSaldo(receitas - despesas);
        } else {
          console.error('Erro: ID do usuário não encontrado');
          Alert.alert('Erro', 'ID do usuário não encontrado');
        }
      } catch (error) {
        console.error('Erro ao buscar dados:', error);
        Alert.alert('Erro', 'Erro ao buscar dados');
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return (
      <View style={styles.loadingContainer}>
        <Text style={styles.loadingText}>Carregando dados...</Text>
      </View>
    );
  }

  // Dados para o gráfico
  const receitasData = dados
    .filter(item => item.tipo === 'RECEITA')
    .map(item => item.valor);
  const despesasData = dados
    .filter(item => item.tipo === 'DESPESA')
    .map(item => item.valor);
  const labels = dados.map(item => item.mes); // Usando os meses como rótulos

  return (
    <View style={styles.container}>
      <Text style={styles.title}>Dashboard de Lançamentos</Text>
      <LineChart
        data={{
          labels: labels,
          datasets: [
            { data: receitasData, color: (opacity = 1) => `rgba(34, 139, 34, ${opacity})`, strokeWidth: 2, label: "Receitas" },
            { data: despesasData, color: (opacity = 1) => `rgba(255, 0, 0, ${opacity})`, strokeWidth: 2, label: "Despesas" },
          ],
          legend: ["Receitas", "Despesas"]
        }}
        width={320} // largura do gráfico
        height={220} // altura do gráfico
        yAxisLabel="R$"
        chartConfig={{
          backgroundColor: '#e26a00',
          backgroundGradientFrom: '#fb8c00',
          backgroundGradientTo: '#ffa726',
          decimalPlaces: 2,
          color: (opacity = 1) => `rgba(255, 255, 255, ${opacity})`,
          style: { borderRadius: 16 }
        }}
        style={styles.chart}
      />
      
      {/* Exibição de Receitas, Despesas e Saldo */}
      <View style={styles.totalsContainer}>
        <Text style={styles.receita}>Total de Receitas: R$ {totalReceitas.toFixed(2)}</Text>
        <Text style={styles.despesa}>Total de Despesas: R$ {totalDespesas.toFixed(2)}</Text>
        <Text style={[styles.saldo, { color: saldo >= 0 ? 'green' : 'red' }]}>Saldo: R$ {saldo.toFixed(2)}</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    paddingVertical: 20,
    backgroundColor: '#fff',
  },
  title: {
    fontSize: 24,
    fontWeight: 'bold',
    textAlign: 'center',
    marginBottom: 20,
    color: '#333',
  },
  chart: {
    marginVertical: 20,
    borderRadius: 16,
    alignSelf: 'center',
  },
  totalsContainer: {
    marginTop: 20,
    paddingHorizontal: 20,
    width: '90%',
    alignItems: 'center',
  },
  receita: {
    fontSize: 18,
    color: 'green',
    marginBottom: 5,
    fontWeight: 'bold',
  },
  despesa: {
    fontSize: 18,
    color: 'red',
    marginBottom: 5,
    fontWeight: 'bold',
  },
  saldo: {
    fontSize: 18,
    fontWeight: 'bold',
    marginTop: 10,
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  loadingText: {
    fontSize: 18,
    color: '#333',
  },
});

export default Dashboard;
