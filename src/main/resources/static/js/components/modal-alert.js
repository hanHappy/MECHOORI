class ModalAlertElement extends HTMLElement{
	constructor(){
	  super();
	 
      let style = `
         .screen{
            position: fixed;
            left:0;
            top:0;
            
            width:100vw;
            height: 100vh;
            z-index: 1300;
            background-color: #000000a0;
            
            display: flex;
            align-items: center;
            justify-content: center;
         }
         
         
         .content-panel{
            background-color: #fff;
            min-width:200px;
            min-height: 100px;
            border-radius: 10px;
            
            
            /*position: relative;
            top: -100px;*/
            
            transform: translateY(-100px);
         
         }
            .content-panel>*{
               padding: 5px 10px;
            }
            
            .content-title{
               border-bottom: 1px solid #979797;   
            }
            
            .content-view{
               border-bottom: 1px solid #979797;
            }
            .content-command{
               
               display: flex;
               justify-content: center;
            }`;
      
      
      
      let title = "제목"
      let content = "내용무";
            
  	  if(this.hasAttribute("data-title"))
  	  content = this.dataset.title;
  	  
      if(this.hasAttribute("data-content"))
      	 content = this.getAttribute("data-content");
      	 
            
      let template = `

         <div class="content-panel">
            <div class="content-title">${title}</div>
            <div class="content-view">
      			${content}
            </div>
            <div class="content-command">
               <button class="btn-ok">확인</button>
               <button class="btn-cancel">취소</button>
            </div>
         </div>
         `;
         
      const wrapper = document.createElement("div");
      wrapper.className = "screen";   
      wrapper.innerHTML = template;
      
      let styleEl = document.createElement("style");       
      styleEl.textContent = style;         
      
      let shadow = this.attachShadow({mode:"open"});
      
      shadow.appendChild(styleEl);
      shadow.appendChild(wrapper);
      
      let btnOk = wrapper.querySelector(".btn-ok")
      btnOk.onclick = () =>{
		  this.remove();
	  }
	}
}


customElements.define("modal-alert", ModalAlertElement);
