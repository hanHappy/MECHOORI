class ModalMenuElement extends HTMLElement {
    constructor(){
        let style = `
            .screen {
                background-color: transparent;
            
                position: fixed;
                top: 0;
                left: 0;
            
                box-sizing: border-box;
                width: 100vw;
                height: 100vh;
                padding-left: 60px;
            }
            
            .panel {
                box-shadow: 0 0 10px 3px #75757520;
            
                min-width: 315px;
                height: 100vh;
            }
            
            .profile-image-wrapper {
                background-color: bisque;
            
                box-sizing: border-box;
                width: 100%;
                height: 200px;
            
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                gap: 10px;
            }
            
            .profile-image-frame {
                border-radius: 50%;
                /* border: 1px solid #fff0; */
                box-shadow: var(--btn-shadow-1);
            
                position: relative;
                overflow: hidden;
            
                width: 110px;
                height: 110px;
            }
            
            .profile-image {
                width: inherit;
                height: inherit;
                position: inherit;
                object-fit: cover;
            }
            
            .menu-list-section {
                box-sizing: border-box;
            
                padding: 50px 20px 0 20px;
                width: 100%;
            
                display: flex;
                align-items: center;
                justify-content: center;
            }
            
            .menu-list-section ul {
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 25px;
            
                width: 100%;
            }
            
            .menu-list-section li {
                box-shadow: var(--btn-shadow-4);
                border-radius: 8px;
            
                min-width: 275px;
                max-width: 400px;
                width: 100%;
                height: 50px;
            }
            
            .menu-list-section a {
                position: relative;
            
                width: 100%;
                height: 100%;
                display: flex;
            
                align-items: center;
                justify-content: center;
            }
            
            .icon {
                background-repeat: no-repeat;
                background-position: center;
                background-size: contain;
            
                position: absolute;
                left: 15px;
            
                height: 25px;
                width: 30px;
            }
            
            .icon-key {
                background-image: url("/images/icons/key.svg");
            }
            
            .icon-heart {
                background-image: url("/images/icons/heart-red.svg");
            }
            
            .icon-review {
                background-image: url("/images/icons/review.svg");
            }
            
            .icon-chart {
                background-image: url("/images/icons/chart.svg");
            }
            
            .icon-power {
                background-image: url("/images/icons/power.svg");
            }
        `
        let template = `
            <div class="screen">
                <div class="panel">
                    <div class="profile-image-wrapper">
                        <div class="profile-image-frame">
                            <img class="profile-image" src="/images/foods/678버거.jpg"  alt="프로필사진">
                        </div>
                        <div class="nickname-wrapper">
                            <span class="nickname" th:text="${m.nickname}">메추리</span>
                        </div>
                    </div>

                    <nav class="menu-list-section">
                        <ul>
                            <li>
                                <a href="my-page/edit-info">
                                    <div class="icon icon-key"></div>
                                    <span>회원 정보 수정</span>
                                </a>
                            </li>
                            <li>
                                <a href="my-page/like-list">
                                    <div class="icon icon-heart"></div>
                                    <span>찜 목록</span>
                                </a>
                            </li>
                            <li>
                                <a href="my-page/rate-list">
                                    <div class="icon icon-review"></div>
                                    <span>평가 목록</span>
                                </a>
                            </li>
                            <li>
                                <a href="my-page/statistics">
                                    <div class="icon icon-chart"></div>
                                    <span>가성비 평가 통계</span>
                                </a>
                            </li>
                            <li>
                                <a href="/logout">
                                    <div class="icon icon-power"></div>
                                    <span>로그아웃</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        `
    } // constructor

    
    show(status){
        if(status)
            this.classList.add("show");
        else
            this.classList.remove("show");
    }   
}

customElements.define("modal-menu", ModalMenuElement);