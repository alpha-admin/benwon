/**
 * Created by samchu on 2017/4/12.
 */
function createAxiosWithToken(block) {
    var instance = axios.create();
    // var token = JSON.parse(localStorage.getItem('askask.sso.token'));
    // instance.defaults.headers.common['Authorization'] = 'bearer ' + token.access_token;
    var token = localStorage.getItem('benwon.accessToken');
    instance.defaults.headers.common['Authorization'] = 'bearer ' + token;
    instance.defaults.validateStatus = function (status) {
        // 因为 401 会跑到 error 去，所以加上后会在 then 区块自己处理
        return (status >= 200 && status < 300) || status == 400 || status == 401 || status == 403 || status == 404 || status == 409;
    }
    if(block != null && block == false){

    }else{
        instance.interceptors.request.use((config) => {
            $.blockUI({
            message: '<img src="/images/loader.gif" />',
            css: {
                top: ($(window).height() - 600) / 2 + 'px',
                left: ($(window).width() - 800) / 2 + 'px',
                width: '800px',
                borderWidth: '0px',
                backgroundColor: 'transparent'
            }
        });
        return config;
    });
        instance.interceptors.response.use((response) => {
            $.unblockUI();
        return response;
    });
    }
    return instance;
}



function axiosGet(uri, success, failure) {
    var instance = createAxiosWithToken();
    instance.get(uri)
        .then(function (response) {
            if (response.status == 200) {
                if(success != null){
                    success(response);
                }
            }else if (response.status == 401) {
                doRefreshToken(function () {
                    axiosGet(uri, success, failure);
                });
            }else{
                console.log(response);
                if(failure != null){
                    failure(response);
                }
            }
        }).catch(function (error) {
        console.log(error);
        $.unblockUI();
    });
}

function axiosPost(uri, data, success, failure) {
    var instance = createAxiosWithToken();
    instance.post(uri, data)
        .then(function (response) {
            if (response.status == 200 || response.status == 201) {
                if(success != null){
                    success(response);
                }
            }else if (response.status == 401) {
                doRefreshToken(function () {
                    axiosPost(uri, data, success, failure);
                });
            }else{
                console.log(response);
                if(failure != null){
                    failure(response);
                }
            }
        }).catch(function (error) {
        	alert('系統忙碌中, 請稍後再試');
        console.log(error);
        $.unblockUI();
    });
}

function axiosPatch(uri, data, success, failure) {
    var instance = createAxiosWithToken();
    instance.patch(uri, data)
        .then(function (response) {
            if (response.status == 200 || response.status == 204) {
                console.log("更新成功");
                if(success != null){
                    success(response);
                }
            }else if (response.status == 401) {
                doRefreshToken(function () {
                    axiosPatch(uri, data, success, failure);
                });
            }else{
                if(failure != null){
                    failure(response);
                }
            }
        }).catch(function (error) {
        console.log(error);
        $.unblockUI();
    });
}

function axiosPut(uri, data, success, failure) {
    var instance = createAxiosWithToken();
    instance.put(uri, data)
        .then(function (response) {
            if (response.status == 200 || response.status == 204) {
                console.log("更新成功");
                if(success != null){
                    success(response);
                }
            }else if (response.status == 401) {
                doRefreshToken(function () {
                    axiosPut(uri, data, success, failure);
                });
            }else{
                if(failure != null){
                    failure(response);
                }
            }
        }).catch(function (error) {
        console.log(error);
        $.unblockUI();
    });
}

function axiosDelete(uri, success, failure) {
    var instance = createAxiosWithToken();
    instance.delete(uri)
        .then(function (response) {
            if (response.status == 200 || response.status == 204) {
                if(success != null){
                    success(response);
                }
            }else if(response.status == 401) {
                doRefreshToken(function () {
                    axiosDelete(uri, success, failure);
                });
            }else{
                console.log(response);
                if(failure != null){
                    failure(response);
                }
            }
        }).catch(function (error) {
        console.log(error);
        $.unblockUI();
    });
}

function doRefreshToken(retry) {
    var doRefresh = axios.create();
    var token = localStorage.getItem('benwon.refreshToken');
//    var token = JSON.parse(localStorage.getItem('kpi.sso'));
    var postData = {
        grant_type: 'refresh_token',
        refresh_token: token
    }
    doRefresh.defaults.headers.common['Authorization'] = '';
    $.blockUI({message: '<img src="../images/loader.gif" />'});
    doRefresh.post('/api/refreshtoken', postData)
        .then(function (response) {
            $.unblockUI();
            if (response.status == 200) {
            	localStorage.setItem('benwon.accessToken', accessToken);
                localStorage.setItem('benwon.refreshToken', refreshToken);
                //localStorage.setItem('kpi.sso', JSON.stringify(response.data));
                retry();
            } else {
            	localStorage.removeItem('benwon.accessToken');
                localStorage.removeItem('benwon.refreshToken');
                alert('請先登入');
                document.location.href = '/line/login';
            }
        }).catch(function (error) {
        	console.log(error);
        	$.unblockUI();
        	alert('請先登入');
            document.location.href = '/line/login';
    });
}