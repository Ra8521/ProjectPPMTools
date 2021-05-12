import React, { Component } from 'react'
import logo from './logo.svg';
import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./components/Project/AddProject";
import { Provider } from "react-redux";
import store from "./store";
class App extends Component {
  render() {
    return (
      <Provider store={store}>
      <Router>
        <div className="App">
          <Header />
          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
        </div>
      </Router>
      </Provider>
    );
  }
}

export default App;
