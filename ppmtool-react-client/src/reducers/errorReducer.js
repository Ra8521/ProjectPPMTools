/* wiring up action type with reducer*/
import { GET_ERRORS } from "../actions/types";


/* set intial state  that is empty object*/
const initialState = {};

export default function(state = initialState, action) {
  switch (action.type) {
    /* if there is error from server, it dispatch the error message to redux store*/
    case GET_ERRORS:
      return action.payload;

    default:
      return state;
  }
}