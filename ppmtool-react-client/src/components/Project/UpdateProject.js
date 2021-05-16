import React, { Component } from "react";
import { getProject } from "../../actions/projectActions";
import PropTypes from "prop-types";
import { connect } from "react-redux";
import classnames from "classnames";
 class UpdateProject extends Component {
     /*an instance of a component is being created and inserted into the DOM:*/
     componentDidMount(){
         /* extracting id props */
         const {id} = this.props.match.params;
       this.props.getProject(id, this.props.history);
     }
  render() {
    return (
      <div className="project">
        <div className="container">
          <div className="row">
            <div className="col-md-8 m-auto">
              <h5 className="display-4 text-center">Update Project form</h5>
              <hr />
              <form>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg "
                    placeholder="Project Name"
                  />
                </div>
                <div className="form-group">
                  <input
                    type="text"
                    className="form-control form-control-lg"
                    placeholder="Unique Project ID"
                    disabled
                  />
                </div>
                <div className="form-group">
                  <textarea
                    className="form-control form-control-lg"
                    placeholder="Project Description"
                  />
                </div>
                <h6>Start Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="start_date"
                  />
                </div>
                <h6>Estimated End Date</h6>
                <div className="form-group">
                  <input
                    type="date"
                    className="form-control form-control-lg"
                    name="end_date"
                  />
                </div>

                <input
                  type="submit"
                  className="btn btn-primary btn-block mt-4"
                />
              </form>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

UpdateProject.propTypes = {
    getProject: PropTypes.func.isRequired,
    project : PropTypes.object.isRequired
    
  };

  /* mapStateToProps is used for selecting the part of the data from the store that the connected component needs.*/
  /*  mapStateToProps function is the entire Redux store state */
const mapStateToProps = state =>({
     /* state.projectReducer.project*/
    project : state.project.project
});

/*
With React Redux, our components never access the store directly - so use connect()
The connect() function connects a React component to a Redux store. 
  2nd argument in connect() method "{getProject}" is actully method to call dispatch that pass data from action to store
*/
export default connect(mapStateToProps, {getProject})(UpdateProject)