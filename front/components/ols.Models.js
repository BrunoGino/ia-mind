"use client"
import React, { useState } from 'react'
import Link from 'next/link'

const requestOptions = {
    method: "GET",
    redirect: "follow"
  };
  
  fetch("http://iamind-alb-1060024196.eu-west-1.elb.amazonaws.com/api/users/students", requestOptions)
    .then((response) => response.text())
    .then((result) => console.log(result))
    .catch((error) => console.error(error));

    console.log(requestOptions)

export default function Models() {

    // For filter
    const students = [
        { id: 1, category: 1, img: "img/models/1.jpg", title: 'Felipe Lenin Alvarenga Siqueira', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Environments', 'Illustration', 'Textures', ''] },
        { id: 2, category: 1, img: "img/models/2.jpg", title: 'Bruno Gino', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Characters', 'Fashion', 'Graphical', 'Photography', 'Environments'] },
        { id: 3, category: 1, img: "img/models/3.jpg", title: 'Gustavo Borges', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Environments', 'Characters', 'Graphical', ''] },
        { id: 4, category: 1, img: "img/models/4.jpg", title: 'Letícia', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Fashion', 'Environments', 'Illustration', 'Textures', ''] },
        { id: 5, category: 1, img: "img/models/5.jpg", title: 'Glaucia M de Oliveira Alvarenga', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Characters', 'Photography', 'Illustration', ''] },
        { id: 6, category: 1, img: "img/models/6.jpg", title: 'Dunha Costa Unha', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Fashion', 'Environments', 'Graphical', 'Textures', 'Characters'] },
        { id: 7, category: 1, img: "img/models/7.jpg", title: 'Ramirez Rodiguez', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Illustration', 'Environments', 'Graphical', 'Textures', ''] },
        { id: 8, category: 1, img: "img/models/8.jpg", title: 'GameVisuals8', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Characters', 'Fashion', 'Photography', ''] },
        { id: 9, category: 1, img: "img/models/9.jpg", title: 'GameVisuals9', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Graphical', 'Characters', 'Illustration', 'Photography', ''] },
        { id: 10, category: 1, img: "img/models/10.jpg", title: 'GameVisuals10', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Textures', 'Characters', 'Fashion', 'Photography', ''] },
        { id: 11, category: 2, img: "img/models/3.jpg", title: 'GameVisuals1', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Environments', 'Illustration', 'Textures', ''] },
        { id: 12, category: 2, img: "img/models/4.jpg", title: 'GameVisuals2', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Characters', 'Fashion', 'Graphical', 'Photography', 'Environments'] },
        { id: 13, category: 2, img: "img/models/5.jpg", title: 'GameVisuals3', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Environments', 'Characters', 'Graphical', ''] },
        { id: 14, category: 2, img: "img/models/6.jpg", title: 'GameVisuals4', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Fashion', 'Environments', 'Illustration', 'Textures', ''] },
        { id: 15, category: 2, img: "img/models/7.jpg", title: 'GameVisuals5', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Characters', 'Photography', 'Illustration', ''] },
        { id: 16, category: 2, img: "img/models/8.jpg", title: 'GameVisuals6', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Fashion', 'Environments', 'Graphical', 'Textures', 'Characters'] },
        { id: 17, category: 2, img: "img/models/9.jpg", title: 'GameVisuals7', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Illustration', 'Environments', 'Graphical', 'Textures', ''] },
        { id: 18, category: 2, img: "img/models/10.jpg", title: 'GameVisuals8', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Characters', 'Fashion', 'Photography', ''] },
        { id: 19, category: 2, img: "img/models/1.jpg", title: 'GameVisuals9', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Graphical', 'Characters', 'Illustration', 'Photography', ''] },
        { id: 20, category: 2, img: "img/models/2.jpg", title: 'GameVisuals10', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Textures', 'Characters', 'Fashion', 'Photography', ''] },
        { id: 21, category: 3, img: "img/models/8.jpg", title: 'GameVisuals1', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Environments', 'Illustration', 'Textures', ''] },
        { id: 23, category: 3, img: "img/models/10.jpg", title: 'GameVisuals3', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Environments', 'Characters', 'Graphical', ''] },
        { id: 24, category: 3, img: "img/models/1.jpg", title: 'GameVisuals4', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Fashion', 'Environments', 'Illustration', 'Textures', ''] },
        { id: 25, category: 3, img: "img/models/2.jpg", title: 'GameVisuals5', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Characters', 'Photography', 'Illustration', ''] },
        { id: 26, category: 3, img: "img/models/3.jpg", title: 'GameVisuals6', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Fashion', 'Environments', 'Graphical', 'Textures', 'Characters'] },
        { id: 27, category: 3, img: "img/models/4.jpg", title: 'GameVisuals7', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Illustration', 'Environments', 'Graphical', 'Textures', ''] },
        { id: 28, category: 3, img: "img/models/5.jpg", title: 'GameVisuals8', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Characters', 'Fashion', 'Photography', ''] },
        { id: 29, category: 3, img: "img/models/6.jpg", title: 'GameVisuals9', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Graphical', 'Characters', 'Illustration', 'Photography', ''] },
        { id: 30, category: 3, img: "img/models/7.jpg", title: 'GameVisuals10', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Textures', 'Characters', 'Fashion', 'Photography', ''] },
        { id: 31, category: 4, img: "img/models/5.jpg", title: 'GameVisuals8', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Buildings', 'Characters', 'Fashion', 'Photography', ''] },
        { id: 32, category: 4, img: "img/models/6.jpg", title: 'GameVisuals9', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Graphical', 'Characters', 'Illustration', 'Photography', ''] },
        { id: 33, category: 4, img: "img/models/7.jpg", title: 'GameVisuals10', hasBookmark: false, desc: "A versatile model great at both photorealism and anime, includes noise offset training... by Lykon.", author_pic: "img/user/user.jpg", author_name: "Caden", tags: ['Textures', 'Characters', 'Fashion', 'Photography', ''] },
        // Add more students with tags here
    ]

    // Initialize your component state
    const [activeIndex, setActiveIndex] = useState(1);
    const [selectedTag, setSelectedTag] = useState('');
    const [bookmarkStates, setBookmarkStates] = useState(students.map(() => false));

    const [hoveredIndex, setHoveredIndex] = useState(null); // Estado para gerenciar hover

    const handleMouseEnter = (index) => setHoveredIndex(index);
    const handleMouseLeave = () => setHoveredIndex(null);

    const handleItemClick = (id) => {
        // Redireciona para o perfil do aluno/professor
        window.location.href = `/student/${id}`;
    };

    const handleOnClick = (index) => {
        setActiveIndex(index);
        setSelectedTag('');
    };

    const filteredStudentsByCategory = activeIndex
        ? students.filter((student) => student.category === activeIndex)
        : null;


    const filteredStudents = selectedTag
        ? filteredStudentsByCategory.filter((student) =>
            student.tags.includes(selectedTag)
        )
        : filteredStudentsByCategory;

    const handleTagChange = (event) => {
        setSelectedTag(event.target.value);
    };

    const hasBookmarkSwitch = (studentId) => {
        // Find the index of the student with the given studentId
        const studentIndex = filteredStudents.findIndex((student) => student.id === studentId);

        if (studentIndex !== -1) {
            // Toggle the bookmark state for the specific student
            const updatedBookmarkStates = [...bookmarkStates];
            updatedBookmarkStates[studentIndex] = !bookmarkStates[studentIndex];

            // Update the bookmark states
            setBookmarkStates(updatedBookmarkStates);
        }
    };




    return (
        <>
            <div className="techwave_fn_models_page">
                <div className="fn__title_holder">
                    <div className="container">
                        <h1 className="title">Finetuned Models</h1>
                    </div>
                </div>
                {/* Models */}
                <div className="techwave_fn_models">
                    {/* <div className="fn__tabs">
                        <div className="container">
                            <div className="tab_in">
                                <a className={activeIndex === 1 ? "active" : ""} onClick={() => handleOnClick(1)}>Main Models</a>
                                <a className={activeIndex === 2 ? "active" : ""} onClick={() => handleOnClick(2)}>Community’s</a>
                                <a className={activeIndex === 3 ? "active" : ""} onClick={() => handleOnClick(3)}>Bookmarks</a>
                                <a className={activeIndex === 4 ? "active" : ""} onClick={() => handleOnClick(4)}>My Own</a>
                            </div>
                        </div>
                    </div>
                    //models filter */}
                    <div className="container">
                        {/* <div className="models__filter">
                            <div className="filter__left">
                                <div className="filter__search">
                                    <input type="text" placeholder="Search gallery" />
                                    <Link href="#" className="techwave_fn_button"><span>Search</span></Link>
                                </div>
                            </div>
                            <div className="filter__right">
                                <div className="filter__category">
                                    <select onChange={handleTagChange}>
                                        <option value="" >Todas as Categorias</option>
                                        <option value="Buildings">Buildings</option>
                                        <option value="Characters">Characters</option>
                                        <option value="Environments">Environments</option>
                                        <option value="Fashion">Fashion</option>
                                        <option value="Illustration">Illustration</option>
                                        <option value="Graphical">Graphical</option>
                                        <option value="Photography">Photography</option>
                                        <option value="Textures">Textures</option>
                                    </select>
                                </div>
                                <div className="filter__order">
                                    <div className="fn__icon_options medium_size align_right">
                                        <span className="fn__icon_button">
                                            <img src="svg/filter.svg" alt="" className="fn__svg" />
                                        </span>
                                        <div className="fn__icon_popup">
                                            <ul>
                                                <li>
                                                    <Link href="#">Newest</Link>
                                                </li>
                                                <li>
                                                    <Link href="#">Oldest</Link>
                                                </li>
                                                <li>
                                                    <Link href="#">A-Z</Link>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> */}
                    </div>
                    {/* !models filter */}
                    <div className="container">
                        {/* models content */}
                        <div className="models__content">
                            <div className="models__results">
                                <div className="fn__preloader">
                                    <div className="icon" />
                                    <div className="text">Loading</div>
                                </div>
                                <div className="fn__tabs_content">
                                    <div id="tab1" className={activeIndex === 1 ? "tab__item active container" : "tab__item container"}>
                                        <ul className="fn__model_items row">
                                            {/*  model item goes here */}{
                                                filteredStudents.map((student, index) => (
                                                    <li key={student.id} className="fn__model_item col-12" onMouseEnter={() => handleMouseEnter(index)} onMouseLeave={handleMouseLeave} onClick={() => handleItemClick(student.id)}>
                                                        <div className="fn__model_content_item">
                                                            {/* Imagem à esquerda */}
                                                            {/*<div className="fn__model_image">
                                                                <img src={student.img} alt={student.title} />
                                                            </div>*/}
                                                            <div className="fn__model_data_items">
                                                                {/* Informações centrais */}
                                                                <div className="fn__model__info">
                                                                    <h3 className="fn__model__title">{student.firstName} {student.lastName}</h3>
                                                                    <h6 className="fn__model__categories">
                                                                        Email: <i>{student.email}</i>
                                                                    </h6>
                                                                    <p className="desc">{student.dateOfBirth}</p>
                                                                    <p className="desc">{student.schoolYear}</p>
                                                                    <p className="desc">{student.classRoom}</p>
                                                                    <p className="desc">{student.gender}</p>
                                                                </div>
                                                                {/* Ações à direita */}
                                                                {hoveredIndex === index && ( // Exibe ações somente no hover
                                                                <div className="fn__model__actions">
                                                                    <div className="fn__actions__hover fn__model_bt_tools">
                                                                        <a href="#"><b>Editar</b></a>
                                                                        <a href="#"><b>Excluir</b></a>
                                                                        <a href="#"><b>Relatórios</b></a>
                                                                    </div>
                                                                </div>
                                                                )}
                                                            </div>
                                                        </div>
                                                    </li>
                                                ))}
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div className="models__more">
                                <Link href="#" className="medium techwave_fn_button"><span>Load More</span></Link>
                            </div>
                        </div>
                        {/* !models content */}
                    </div>
                </div>
                {/* !Models */}
            </div>

        </>
    )
}
