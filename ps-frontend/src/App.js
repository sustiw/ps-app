import React  ,{useState} from "react";
import './App.css';
import CCHeader from './component/cc-header';
import CCForm from './component/cc-form';
import CCList from './component/cc-list';

const addCardAPI="http://localhost:8080/creditCards/add";
const cardListAPI="http://localhost:8080/creditCards/getAll";




function App() {
  
const [card,setCard] = useState ({
  name:'',cardNumber:'',limit:0,balance:0
  }
);


const [cardList,setCardList] = useState ([]);


const handleInputValue =event => {
  let obj ={[event.target.name] : event.target.value};
  setCard( ()=>({...card ,...obj }));
}

  
const addCard = async () => {
   
                      const config = {
                          method: 'POST',
                          headers: {
                              'Accept': 'application/json',
                              'Content-Type': 'application/json',
                          },
                          body: JSON.stringify(card)
                      }
                      const response = await fetch(addCardAPI, config)
                      if (response.status===200) {
                          setCard({name:'',cardNumer:'',limit:''});
                      } else if(response.status===400){
                        alert(" name,creditCard can  not be empty. Credit card not not be more then 19 digit.Limit can not be set to 0 . ");
                      }else if(response.status===409){
                        alert("Existing card can not be added");

                      }else{
                        alert("problem with backed server");
                      }
                      getCard();
              
}


const getCard = async () => {

    const response = await fetch(cardListAPI)

    const data = await response.json()


    if (response.ok) {
        setCardList(data);
    } else{
      alert("problem with backed server while fetch card list");
    }
  }
  return (
    <div >

<CCHeader/>
 <CCForm card={card} handleInputValue={handleInputValue}  addCard={addCard} />
 <CCList cards={cardList}/>
    </div>
  );
}

export default App;
