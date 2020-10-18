import React from 'react';
import './App.css';
import { Container, Row, Col } from 'react-bootstrap';
import Header from './Components/Header';
import 'bootstrap/dist/css/bootstrap.min.css';
import Actions from './Components/Actions';
import Welcome from './Pages/Welcome';
// import NewsList from './Pages/NewsList';


function App() {
  return (
    <div className="App">
    <Container>
      <Header/>
    </Container>
    </div>
  )
}

export default App;
