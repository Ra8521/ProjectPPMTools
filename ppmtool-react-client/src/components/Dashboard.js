import React, { Component } from 'react'
import ProjectItem from './Project/ProjectItem';
import CreateProjectButton from "./Project/CreateProjectButton";
import "bootstrap/dist/css/bootstrap.min.css";

import { connect } from "react-redux";
import { getProjects } from "../actions/projectActions";
import PropTypes from "prop-types";
class Dashboard extends Component {
    componentDidMount(){
        this.props.getProjects();
    }
    render() {
        /* create props in Dashboard component for learning puropse how  props pass from parent element to child */
       
       /* const projectObj = {
            projectName : "project name props",
            projectIdentifier: "Identifier props",
            description:"decription props"
        };
        */
       /* extracting props project.projects of dashboard component*/
        const { projects } = this.props.project;
        return (

            <div className="projects">
            <div className="container">
                <div className="row">
                    <div className="col-md-12">
                        <h1 className="display-4 text-center">Projects</h1>
                        <br />
                        <CreateProjectButton/>
                        <br />
                        <hr />
                {/* here passing projectObj props of Dashboard component  to projectItem component props projectProps */}
                  {/*  <ProjectItem project = {projectObj}/>  */}
                {/* Iterating project list and map to projectItem props */}
                  {projects.map(project => (
                    <ProjectItem key={project.id} project={project} />
                  ))}


                    </div>
                </div>
            </div>
        </div>
    
        );
    }
}

/* the type of project projs is object */
Dashboard.propTypes = {
    project: PropTypes.object.isRequired,
    getProjects: PropTypes.func.isRequired
  };

/* mapping application state to props("project") of Dashboard component*/
const mapStateToProps = state => ({
    project: state.project
  });

/* here, connect(arg1, arg2) is actually, collecting data of state of application from backend through(getProjects) and 
map to props of frontend component Dashboard*/
export default connect(
    mapStateToProps,
    { getProjects }
  )(Dashboard);
