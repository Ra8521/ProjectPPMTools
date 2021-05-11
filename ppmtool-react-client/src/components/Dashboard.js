import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem';
import "bootstrap/dist/css/bootstrap.min.css";
class Dashboard extends Component {
    render() {
        return (
            <div className="Dashboard">
            <h1 className="alert alert-warning"> Welcome to dashboard </h1>
            <ProjectItem/>
            <ProjectItem/>
            <ProjectItem/>
            </div>
        );
    }
}

export default Dashboard
