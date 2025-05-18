import React from "react";
import CCItem from "./cc-item";


const CCList =props=>{

    return  (

        <div>
        <h2 className="card-title">Existing Cards</h2>
        {
             <CCItem cards={props.cards}/>
        }       
       </div>  
    )

}

export default CCList;