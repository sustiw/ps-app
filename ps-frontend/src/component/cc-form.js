import React  from "react";


const CCForm = props =>{

    return  (

      

                        <div className="card-body col-4">
                                        <h2 className="card-title">Add</h2>
                                        <form className="row needs-validation" >
                                                    <div className="mb-1">
                                                        <label  className="form-label">Name</label>
                                                        <input type="text" name="name" className="form-control" 
                                                        onChange={props.handleInputValue} value={props.card.name} required/>
                                                    </div>
                                                  
                                                    
                                                    <div className="mb-1">
                                                        <label  className="form-label">Card Number</label>
                                                        <input type="text" name="cardNumber" className="form-control" 
                                                         onChange={props.handleInputValue} value={props.card.cardNumber} required/>
                                                    </div>
                                                   
                                                    <div className="mb-1">
                                                        <label  className="form-label">Limit</label>
                                                        <input type="text" name="limit" className="form-control" 
                                                          onChange={props.handleInputValue} value={props.card.limit} required/>
                                                    </div>
                                                  
                                                    <div className="mb-1">
                                                    <button onClick={props.addCard} type="button" className="btn btn-secondary btn-lg" >Add</button>
                                                    </div>
                                        </form>

                                        
                        </div>
        


    )

}

export default CCForm;