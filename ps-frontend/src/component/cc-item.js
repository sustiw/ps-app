import React from "react";


const CCItem =props=>{

    return  (

        <div>
        
                <div className="card w-75 mb-3">
                        <div className="card-body">
                                       
                                <table className="table">
                                    <thead>
                                        <tr  className="table-secondary">
                                        <th scope="col">Name</th>
                                        <th scope="col">Card Number</th>
                                        <th scope="col">Balance</th>
                                        <th scope="col">Limit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                        props.cards.map(card =>
                                                <tr key={card.id}>
                                                <td>{card.name}</td>
                                                <td>{card.cardNumber}</td>
                                                <td>{card.balance}</td>
                                                <td>{card.limit}</td>
                                                </tr>
                                        )
                                        }
                                      
                                    </tbody>
                            </table>
                                        
                        </div>
                </div>  
    
     </div>
    )

}

export default CCItem;