import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem';

class Dashboard extends Component {
    render() {
        return (
            <div className="Dashboard">
            <h1> Welcome to dashboard </h1>
            <ProjectItem/>
            <ProjectItem/>
            <ProjectItem/>
            </div>
        );
    }
}

export default Dashboard
