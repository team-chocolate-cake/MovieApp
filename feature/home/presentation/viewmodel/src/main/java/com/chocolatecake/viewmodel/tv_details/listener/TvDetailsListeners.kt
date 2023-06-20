package com.chocolatecake.viewmodel.tv_details.listener

import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.listener.PeopleListener

interface TvDetailsListeners : RateListener, PeopleListener, MediaListener,
    SeasonListener, ShowMoreCast, ShowMoreRecommended {

}